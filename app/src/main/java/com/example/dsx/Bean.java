package com.example.dsx;

import java.util.List;

public class Bean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : item1
         * select : true
         */

        private String name;
        private boolean select;
        private boolean mSelect;

        public boolean getmSelect() {
            return mSelect;
        }

        public void setmSelect(boolean mSelect) {
            this.mSelect = mSelect;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }
}
