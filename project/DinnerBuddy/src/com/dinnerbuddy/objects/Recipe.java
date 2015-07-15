package com.dinnerbuddy.objects;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable{

	public Recipe()
	{
	
	}
	public String Name;
	public String SmallImageURL;
	public String LargeImageURL;
	public String Id;
	public ArrayList<String> Ingrediants;
	public double Rating;
	public int Yields;
	public int Duration;
	public String RecipeURL;
	
	
	
	 // Parcelling part
    public Recipe(Parcel in){
       
        this.Name = in.readString();
        this.SmallImageURL = in.readString();
        this.Id = in.readString();
    }
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.Name);
		dest.writeString(this.SmallImageURL);
		dest.writeString(this.Id);
		
	}
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in); 
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

}
