package com.example.humanresources.business.enums;


public class EnumCollection {
    public enum UserType {
        User(1), Admin(2), Employer(3),Candidate(4);
        private final int value;

        private UserType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String toString() {
            return Integer.toString(value);
        }


    }
/*Example Use Enum in Java*/
/*    public static void main(String[] args) {
        System.out.println(DownloadType.AUDIO.getValue());           //returns 1
        System.out.println(DownloadType.VIDEO.getValue());           //returns 2
        System.out.println(DownloadType.AUDIO_AND_VIDEO.getValue()); //returns 3
    }*/
}




