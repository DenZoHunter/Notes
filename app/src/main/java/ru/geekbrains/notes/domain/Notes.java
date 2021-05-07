package ru.geekbrains.notes.domain;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Notes implements Parcelable {

    @StringRes
    private final int nameNotes;
    @StringRes
    private final int descriptionNotes;
    @StringRes
    private final int dateNotes;

    public Notes(int nameNotes, int descriptionNotes, int dateNotes) {
        this.nameNotes = nameNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = dateNotes;
    }

    protected Notes(Parcel in) {
        nameNotes = in.readInt();
        descriptionNotes = in.readInt();
        dateNotes = in.readInt();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public int getNameNotes() {
        return nameNotes;
    }

    public int getDescriptionNotes() {
        return descriptionNotes;
    }

    public int getDateNotes() {
        return dateNotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nameNotes);
        dest.writeInt(descriptionNotes);
        dest.writeInt(dateNotes);
    }
}
