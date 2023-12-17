package exercise;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Getter
@Setter
public class IndexBean implements Serializable {
    private String name;
    private List<String> list;

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
    }

    public void add() {
        list.add(name);
    }

    public void remove(String name){
        list.removeIf(s -> s.equals(name));
    }
}
