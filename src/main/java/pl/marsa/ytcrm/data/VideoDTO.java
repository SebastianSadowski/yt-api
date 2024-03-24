package pl.marsa.ytcrm.data;

import java.util.List;

public record  VideoDTO(String name, Byte[] source, List<String> tags, Visibility access) {}
