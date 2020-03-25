import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;
import net.bramp.unsafe.UnsafeHelper;
import sun.misc.Unsafe;

public class Main {
  public static void main(String[] args) throws ReflectiveOperationException {
    Unsafe unsafe = UnsafeHelper.getUnsafe();
    Class jdkInternalMiscUnsafeClass = Class.forName("jdk.internal.misc.Unsafe");
    Field accessible = AccessibleObject.class.getDeclaredField("override");
    long offset = unsafe.objectFieldOffset(accessible);
    unsafe.putBoolean(jdkInternalMiscUnsafeClass, offset, true);
    String name;
    Field theInstance = jdkInternalMiscUnsafeClass.getDeclaredField("theUnsafe");
    theInstance.setAccessible(true);
    System.out.println(theInstance.get(null));
  }
}
