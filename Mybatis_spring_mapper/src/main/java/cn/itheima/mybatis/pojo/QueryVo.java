package cn.itheima.mybatis.pojo;

import java.util.List;

public class QueryVo {

    private User user;

    //传递多个用户的id
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
