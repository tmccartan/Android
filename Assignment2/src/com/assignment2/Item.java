package com.assignment2;

import android.os.Parcel;
import android.os.Parcelable;


public class Item implements Parcelable {
	
	 public  String name;
	 public String initial;
	 public String colour;
	 public int image;
	 
	 public Item (String name, int imageRes, String colour)
	 {
		 this.name = name;
		 this.initial = name.substring(0, 1);
		 this.image = imageRes;
		 this.colour = colour;
		 
	 }
	 // Parcelling part
     public Item(Parcel in){
        
         this.name = in.readString();
         this.initial = in.readString();
         this.image = in.readInt();
         this.colour = in.readString();
     }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		 dest.writeString(this.name);
		 dest.writeString(this.initial);
		 dest.writeInt(this.image);
		 dest.writeString(this.colour);
		
	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Item createFromParcel(Parcel in) {
            return new Item(in); 
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };


}
