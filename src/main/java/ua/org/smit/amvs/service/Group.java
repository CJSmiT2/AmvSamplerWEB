package ua.org.smit.amvs.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ua.org.smit.commontlx.filesystem.FolderCms;
import ua.org.smit.commontlx.filesystem.TxtFile;

public class Group {

    private final String groupName;
    private final List<FolderCms> titles = new ArrayList<>();

    Group(String name, String completePath, FolderCms groupsFolder) {
        this.groupName = name;
        initTitles(groupName, completePath, groupsFolder);
    }

    private void initTitles(String name, String completePath, FolderCms groupsFolder) {
        TxtFile txt = new TxtFile(groupsFolder + File.separator + name);
        for (String title : txt.readByLines()) {
            titles.add(new FolderCms(completePath + File.separator + title));
        }
    }

    public List<Sample> getSamples() {
        List<Sample> list = new ArrayList<>();

        for (FolderCms title : titles) {
            for (FolderCms fragment : title.getFoldersCms()) {
                list.add(new Sample(fragment));
            }
        }

        return list;
    }

    public Sample getSample(String fragmentName) {

        for (FolderCms title : titles) {
            for (FolderCms fragment : title.getFoldersCms()) {
                if (fragment.getName().equals(fragmentName)) {
                    return new Sample(fragment);
                }
            }
        }

        throw new RuntimeException("Cant find fragment '" + fragmentName + "'");
    }

    public String getName() {
        return groupName;
    }
    
    public String getClearName() {
        return groupName.replaceAll("_", " ");
    }

}
