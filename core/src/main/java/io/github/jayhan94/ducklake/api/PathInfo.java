package io.github.jayhan94.ducklake.api;

public class PathInfo {
    private final String path;
    private final boolean pathIsRelative;

    public PathInfo(String path, boolean pathIsRelative) {
        this.path = path;
        this.pathIsRelative = pathIsRelative;
    }

    public String path() {
        return path;
    }

    public boolean pathIsRelative() {
        return pathIsRelative;
    }
}
