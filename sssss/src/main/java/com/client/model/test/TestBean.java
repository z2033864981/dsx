package com.client.model.test;

import java.util.List;

public class TestBean {

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
        public boolean currentSelect;

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
            this.currentSelect = select;
        }
    }
}
