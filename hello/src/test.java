import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        //A<String> stringA = new A<String>();
        //A<Objects> stringA1 = new A<String>();//错误
        //A<String> stringA = new A<>();
        A<?> a = new A<p<String>>(new p<String>());
        //A<t<Integer,String>> stringB = new A<>();
        B b = new B();
        //b.e(new A<q<?>>(new q<String>()));
        //b.e(stringB);
        //q<String> stringInteger = new t<String,Integer>();
        A<Object> objectA = new A<Object>(1);
        System.out.println(objectA.e.getClass());
        A<?> objectA1 = new A<>(1);
        System.out.println(objectA1.e.getClass());

        B b1 = new B();
        b1.e(new A<>("1"));
        B b2 = new B();
    }
}
class A<E>{
    E e;
    public A() {
    }
    public A(E e) {
        this.e = e;
    }
}
class B{

    public void e(A<String> l){}
    public void h(ArrayList<?> p){}
}
class q<T>{}
class p<T> extends q<T>{}

