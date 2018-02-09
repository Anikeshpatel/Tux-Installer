package grp.anon.tux;

import java.io.File;

public class Design {
    File software,icon;
    String softwareName;
    final String softwareType = "Utility";
    public Design(File software,File icon){
        this.software = software;
        this.icon = icon;
        this.softwareName = software.getName();
        System.out.println(softwareName);
    }
}
