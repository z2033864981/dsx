package com.example.demo2.bean;

import java.util.List;

public class TwooBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"count":20,"totalPages":2,"pageSize":10,"currentPage":2,"data":[{"id":281,"title":"条纹新风尚","price_info":29,"scene_pic_url":"https://yanxuan.nosdn.127.net/14926859849200826.jpg","subtitle":"经典百搭，时尚线条"},{"id":282,"title":"成就一室笋香","price_info":12,"scene_pic_url":"https://yanxuan.nosdn.127.net/14927695046601069.jpg","subtitle":"三石哥办公室常备小食推荐"},{"id":283,"title":"孩子成长中少不了的一双鞋","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14927748974441080.jpg","subtitle":"说起毛毛虫鞋，好处实在太多了，作为一个2岁孩子的宝妈选品员，按捺不住想告诉大家，..."},{"id":277,"title":"治愈生活的满怀柔软","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14926737925770587.jpg","subtitle":"太鼓抱枕的上架历程，是从失踪开始的。由于表面的绒感，最初它被安排在秋冬季上架。某..."},{"id":274,"title":"没有软木拖，怎么过夏天","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14925822213780237.jpg","subtitle":"刚入四月，杭州的气温就已升高至30度。店庆时买了软木拖的用户，陆续发回评价说，很..."},{"id":272,"title":"料理也要精细简单","price_info":69,"scene_pic_url":"https://yanxuan.nosdn.127.net/14925200530030186.jpg","subtitle":"享受天然的味道，日子每天都好新鲜"},{"id":271,"title":"选式新懒人","price_info":15,"scene_pic_url":"https://yanxuan.nosdn.127.net/14924199099661697.jpg","subtitle":"懒出格调，懒出好生活。"},{"id":268,"title":"米饭好吃的秘诀：会呼吸的锅","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14920623353130483.jpg","subtitle":"今年1月份，我们联系到了日本伊贺地区的长谷园，那里有着180年伊贺烧历史的窑厂。..."},{"id":266,"title":"一条丝巾就能提升时髦度","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14919007135160213.jpg","subtitle":"不知道大家对去年G20时，严选与国礼制造商一起推出的《凤凰于飞》等几款丝巾是否还..."},{"id":264,"title":"设计师们推荐的应季好物","price_info":29.9,"scene_pic_url":"https://yanxuan.nosdn.127.net/14918201901050274.jpg","subtitle":"原创设计春款系列上新"}]}
     */

    private int errno;
    private String errmsg;
    private DataBeanX data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * count : 20
         * totalPages : 2
         * pageSize : 10
         * currentPage : 2
         * data : [{"id":281,"title":"条纹新风尚","price_info":29,"scene_pic_url":"https://yanxuan.nosdn.127.net/14926859849200826.jpg","subtitle":"经典百搭，时尚线条"},{"id":282,"title":"成就一室笋香","price_info":12,"scene_pic_url":"https://yanxuan.nosdn.127.net/14927695046601069.jpg","subtitle":"三石哥办公室常备小食推荐"},{"id":283,"title":"孩子成长中少不了的一双鞋","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14927748974441080.jpg","subtitle":"说起毛毛虫鞋，好处实在太多了，作为一个2岁孩子的宝妈选品员，按捺不住想告诉大家，..."},{"id":277,"title":"治愈生活的满怀柔软","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14926737925770587.jpg","subtitle":"太鼓抱枕的上架历程，是从失踪开始的。由于表面的绒感，最初它被安排在秋冬季上架。某..."},{"id":274,"title":"没有软木拖，怎么过夏天","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14925822213780237.jpg","subtitle":"刚入四月，杭州的气温就已升高至30度。店庆时买了软木拖的用户，陆续发回评价说，很..."},{"id":272,"title":"料理也要精细简单","price_info":69,"scene_pic_url":"https://yanxuan.nosdn.127.net/14925200530030186.jpg","subtitle":"享受天然的味道，日子每天都好新鲜"},{"id":271,"title":"选式新懒人","price_info":15,"scene_pic_url":"https://yanxuan.nosdn.127.net/14924199099661697.jpg","subtitle":"懒出格调，懒出好生活。"},{"id":268,"title":"米饭好吃的秘诀：会呼吸的锅","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14920623353130483.jpg","subtitle":"今年1月份，我们联系到了日本伊贺地区的长谷园，那里有着180年伊贺烧历史的窑厂。..."},{"id":266,"title":"一条丝巾就能提升时髦度","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14919007135160213.jpg","subtitle":"不知道大家对去年G20时，严选与国礼制造商一起推出的《凤凰于飞》等几款丝巾是否还..."},{"id":264,"title":"设计师们推荐的应季好物","price_info":29.9,"scene_pic_url":"https://yanxuan.nosdn.127.net/14918201901050274.jpg","subtitle":"原创设计春款系列上新"}]
         */

        private int count;
        private int totalPages;
        private int pageSize;
        private int currentPage;
        private List<DataBean> data;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 281
             * title : 条纹新风尚
             * price_info : 29
             * scene_pic_url : https://yanxuan.nosdn.127.net/14926859849200826.jpg
             * subtitle : 经典百搭，时尚线条
             */

            private int id;
            private String title;
            private String price_info;
            private String scene_pic_url;
            private String subtitle;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice_info() {
                return price_info;
            }

            public void setPrice_info(String price_info) {
                this.price_info = price_info;
            }

            public String getScene_pic_url() {
                return scene_pic_url;
            }

            public void setScene_pic_url(String scene_pic_url) {
                this.scene_pic_url = scene_pic_url;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }
        }
    }
}
