package wb3.core.builder;

import java.util.List;

/**
 * Created by WBensing on 3/4/2017.
 */
public interface IObjectBuilder<FromType, ToType>
{
    ToType ToObject(FromType fromObject);
    FromType FromObject(ToType toObject);
    ToType EmptyObject(Class<ToType> clazz) throws IllegalAccessException, InstantiationException;
    List<ToType> EmptyList();
    List<ToType> ToList(List<FromType> list);
}
