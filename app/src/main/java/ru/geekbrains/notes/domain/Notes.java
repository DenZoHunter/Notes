
package ru.geekbrains.notes.domain;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Notes implements Parcelable {

    private final String nameNotes;
    private final String descriptionNotes;
    private final String dateNotes;

    public Notes(String nameNotes, String descriptionNotes, String dateNotes) {
        this.nameNotes = nameNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = dateNotes;
    }

    protected Notes(Parcel in) {
        nameNotes = in.readString();
        descriptionNotes = in.readString();
        dateNotes = in.readString();
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

    public String getNameNotes() {
        return nameNotes;
    }

    public String getDescriptionNotes() {
        return descriptionNotes;
    }

    public String getDateNotes() {
        return dateNotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameNotes, descriptionNotes, dateNotes);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameNotes);
        dest.writeString(descriptionNotes);
        dest.writeString(dateNotes);
    }
}
