
package ru.geekbrains.notes.domain;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class Note implements Parcelable {

    private final String nameNotes;
    private final String descriptionNotes;
    private final Date dateNotes;

    public Note(String nameNotes, String descriptionNotes, Date dateNotes) {
        this.nameNotes = nameNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = dateNotes;
    }

    protected Note(Parcel in) {
        nameNotes = in.readString();
        descriptionNotes = in.readString();
        dateNotes = (Date) in.readSerializable();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(nameNotes, note.nameNotes) &&
                Objects.equals(descriptionNotes, note.descriptionNotes) &&
                Objects.equals(dateNotes, note.dateNotes);
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getNameNotes() {
        return nameNotes;
    }

    public String getDescriptionNotes() {
        return descriptionNotes;
    }

    public Date getDateNotes() {
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
        dest.writeSerializable(dateNotes);
    }
}
