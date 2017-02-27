package fr.margottetournicote.doudoulist;

/**
 * Created by clement on 27/02/17.
 */

public class MaListItem {
    public String nom = "";
    public boolean checked = false;

    public MaListItem(String nom) {
        this.nom = nom;
    }

    public MaListItem(String nom, boolean checked) {
        this.nom = nom;
        this.checked = checked;
    }
}
