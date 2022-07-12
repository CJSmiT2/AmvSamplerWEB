package ua.org.smit.amvs.service;

import ua.org.smit.commontlx.filesystem.FileCms;
import ua.org.smit.commontlx.filesystem.FolderCms;

public class Sample {

    private FileCms gif;
    private FileCms mp4;
    private final String fragment;

    Sample(FolderCms fragment) {
        this.fragment = fragment.getName();
        for (FileCms file : fragment.getFilesTlx()) {
            if (file.getExtension().equalsIgnoreCase("gif")) {
                gif = file;
            }
            if (file.getExtension().equalsIgnoreCase("mp4")) {
                mp4 = file;
            }
        }
    }

    public FileCms getGif() {
        return gif;
    }

    public FileCms getMp4() {
        return mp4;
    }

    public String getFragment() {
        return fragment;
    }

    public boolean isNotEmpty() {
        return (gif != null) && (mp4 != null);
    }

}
