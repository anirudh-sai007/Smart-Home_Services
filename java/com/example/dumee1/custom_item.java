package com.example.dumee1;

public class custom_item
{
        private String spinnerItemName;
        private int spinnerItemImage;

        public custom_item(String spinnerItemName, int spinnerItemImage) {
            this.spinnerItemName = spinnerItemName;
            this.spinnerItemImage = spinnerItemImage;
        }

        public String getSpinnerItemName() {
            return spinnerItemName;
        }

        public int getSpinnerItemImage() {
            return spinnerItemImage;
        }
}