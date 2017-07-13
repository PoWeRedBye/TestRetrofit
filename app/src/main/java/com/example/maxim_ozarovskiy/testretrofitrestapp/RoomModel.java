package com.example.maxim_ozarovskiy.testretrofitrestapp;



import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim_Ozarovskiy on 12.07.2017.
 */

public class RoomModel {
    private int roomId;

    private String name;

    @SerializedName("threads")
    private int postsCount;

    @SerializedName("containsUnreadThreads")
    private String containsUnreadPosts;




}
