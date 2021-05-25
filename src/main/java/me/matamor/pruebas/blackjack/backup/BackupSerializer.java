package me.matamor.pruebas.blackjack.backup;

import me.matamor.pruebas.lib.serializer.SerializationException;
import me.matamor.pruebas.lib.serializer.Serializer;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BackupSerializer implements Serializer<Backup> {

    @Override
    public Object serialize(Backup value) throws SerializationException {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Output", value.getOutput().getAbsolutePath());
        map.put("Files", value.getFiles().stream().map(File::getAbsolutePath).collect(Collectors.toList()));
        map.put("Directories", value.getDirectories().stream().map(File::getAbsolutePath).collect(Collectors.toList()));

        return map;
    }

    @Override
    public Backup deserialize(Object serialized) throws SerializationException {
        Map<String, Object> map = asMap(serialized);

        File output = new File(get(map, "Output", String.class));

        List<File> files = (List<File>) get(map, "Files", List.class).stream()
                .filter(e -> e instanceof String)
                .map(o -> new File((String) o)).collect(Collectors.toList());

        List<File> directories = (List<File>) get(map, "Directories", List.class).stream()
                .filter(e -> e instanceof String)
                .map(o -> new File((String) o)).collect(Collectors.toList());

        return new Backup(output, files, directories);
    }
}
