package wb3.core.builder;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectBuilder<From, To> implements IObjectBuilder<From, To> {

    public To EmptyObject(Class<To> clazz) throws IllegalAccessException, InstantiationException {
        To result = clazz.newInstance();
        return result;
    }

    public List<To> EmptyList()
    {
        return new ArrayList<To>();
    }

    public List<To> ToList(List<From> list) {
        List<To> result = new ArrayList<To>();
        for(From item : list){
            result.add(ToObject(item));
        }
        return result;
    }

    public abstract To ToObject(From fromObject);

    public abstract From FromObject(To toObject);

}
