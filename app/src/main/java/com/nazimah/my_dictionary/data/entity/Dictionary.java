package com.nazimah.my_dictionary.data.entity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dictionary")
public class Dictionary implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String word;
    public String remark;
    public String meaning;

    public Dictionary(){}
    protected Dictionary(Parcel in) {
        id = in.readInt();
        word = in.readString();
        remark = in.readString();
        meaning = in.readString();
    }

    public static final Creator<Dictionary> CREATOR = new Creator<Dictionary>() {
        @Override
        public Dictionary createFromParcel(Parcel in) {
            return new Dictionary(in);
        }

        @Override
        public Dictionary[] newArray(int size) {
            return new Dictionary[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(word);
        parcel.writeString(remark);
        parcel.writeString(meaning);
    }


    @Override
    public String toString() {
        return "Dictionary{" +
                "id="+ id +
                ",word = "+ word +
                ",remark='"+ remark+'\''+
                ",meaning='"+ meaning+'\''+
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
