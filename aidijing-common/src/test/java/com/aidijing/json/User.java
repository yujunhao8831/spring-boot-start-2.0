package com.aidijing.json;

/**
 * @author : 披荆斩棘
 * @date : 2017/5/25
 */

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String         name;
    private String[]       names;
    private List< String > info;
    private Date           time;

    public User () {
        this.name = "11";
        this.names = new String[]{ "1" , "3" , "9" };
        this.info = Arrays.asList( "北京", "朝阳" );
        this.time = new Date();
    }

    public String getName () {
        return name;
    }

    public User setName ( String name ) {
        this.name = name;
        return this;
    }

    public String[] getNames () {
        return names;
    }

    public User setNames ( String[] names ) {
        this.names = names;
        return this;
    }

    public List< String > getInfo () {
        return info;
    }

    public User setInfo ( List< String > info ) {
        this.info = info;
        return this;
    }

    public Date getTime () {
        return time;
    }

    public User setTime ( Date time ) {
        this.time = time;
        return this;
    }


    @Override
    public String toString () {
        return "User{" +
                "name='" + name + '\'' +
                ", names=" + Arrays.toString( names ) +
                ", info=" + info +
                ", time=" + time +
                '}';
    }
}
