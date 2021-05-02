package ru.geekbrains.notes.domain;


import androidx.annotation.StringRes;

public class Notes {

    @StringRes
    private int nameNotes;
    @StringRes
    private int descriptionNotes;
    @StringRes
    private int dateNotes;

    public Notes(int nameNotes, int descriptionNotes, int dateNotes) {
        this.nameNotes = nameNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = dateNotes;
    }

    public int getNameNotes() {
        return nameNotes;
    }

    public void setNameNotes(int nameNotes) {
        this.nameNotes = nameNotes;
    }

    public int getDescriptionNotes() {
        return descriptionNotes;
    }

    public void setDescriptionNotes(int descriptionNotes) {
        this.descriptionNotes = descriptionNotes;
    }

    public int getDateNotes() {
        return dateNotes;
    }

    public void setDateNotes(int dateNotes) {
        this.dateNotes = dateNotes;
    }
}
