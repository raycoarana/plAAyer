import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.stream.Stream;

public class ChannelsGeneration extends DefaultTask {

    private static final String COMMAND_STREAMLINK = "streamlink -l debug --stream-url ";

    private String cleanUpStreamIdsReplaceRegEx = "\\(.*\\)";

    @TaskAction
    public void generate() throws FileNotFoundException {
        File channelsDefinitionsFile = new File(getProject().getRootDir(), "build-definitions/channels.json");
        JsonElement rootElement = new JsonParser().parse(new FileReader(channelsDefinitionsFile));
        JsonArray channels = rootElement.getAsJsonObject().getAsJsonArray("channels");

        channels.forEach(jsonElement -> fillWithStreams(jsonElement.getAsJsonObject()));

        File destinationFile = new File(getProject().getRootDir(), "mobile/src/main/res/raw/channels.json");
        try (FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
            outputStream.write(rootElement.toString().getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillWithStreams(JsonObject channel) {
        System.out.println("Generating channel: " + channel.get("title").getAsString());

        String sourceUrl = channel.get("source").getAsString();
        String availableStreamsCommand = COMMAND_STREAMLINK + sourceUrl;

        String result = execute(availableStreamsCommand);
        String streamIds[] = result.split(":")[1].split(", ");
        streamIds = Stream.of(streamIds).map((id) -> id.trim().replaceAll(cleanUpStreamIdsReplaceRegEx, "")).toArray(String[]::new);

        System.out.println("Found " + streamIds.length + " streams.");

        JsonObject streamsJson = new JsonObject();
        for (String streamId : streamIds) {
            if (Objects.equals(streamId, "No plugin can handle URL") || Objects.equals(streamId, "No playable streams found on this URL")) {
                streamsJson.add("default", new JsonPrimitive(channel.get("default").getAsString()));
                continue;
            }
            String streamUrl = execute(availableStreamsCommand + " " + streamId);

            System.out.println(streamId + " : " + streamUrl);

            streamsJson.add(streamId, new JsonPrimitive(streamUrl));
        }
        channel.add("streams", streamsJson);
    }

    private String execute(String cmd) {
        try {
            final Process p = Runtime.getRuntime().exec(cmd);

            StringBuilder outputBuilder = new StringBuilder();
            new Thread(() -> {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;

                try {
                    while ((line = input.readLine()) != null) {
                        outputBuilder.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            p.waitFor();
            return outputBuilder.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}