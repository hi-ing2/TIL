# c# 제너릭

```c#
// 어떤 요소 타입도 받아들 일 수 있는
// 스택 클래스를 C# 제네릭을 이용하여 정의
class MyStack<T> // 꺽쇠 괄호를 사용
{
    T[] _elements;
    int pos = 0;

    public MyStack()
    {
        _elements = new T[100];
    }

    public void Push(T element)
    {
        _elements[++pos] = element;
    }

    public T Pop()
    {
        return _elements[pos--];
    }
}

// 두 개의 서로 다른 타입을 갖는 스택 객체를 생성
MyStack<int> numberStack = new MyStack<int>(); // 내부적으로 int형만 받는 리스트를 생성하게 됨.
MyStack<string> nameStack = new MyStack<string>(); // 내부적으로 string형만 받는 리스트를 성성하게 됨.
```



**제네릭 타입 제약 (Type Constraint)**

> C# 제네릭 타입을 선언할 때, 타입 파라미터가 Value Type인지 Reference Type인지, 또는 어떤 특정 Base 클래스로부터 파생된 타입인지, 어떤 인터페이스를 구현한 타입인지 등등을 지정할 수 있는데, 이는 **where T : 제약조건** 과 같은 식으로 where 뒤에 제약 조건을 붙이면 가능하다. 아래는 다양한 제약을 가한 예제들이다. 즉, 제한된 타입만 사용할 수 있다느 ㄴ것이다.

```c#
// T는 Value 타입
class MyClass<T> where T : struct 

// T는 Reference 타입
class MyClass<T> where T : class

// T는 디폴트 생성자를 가져야 함
class MyClass<T> where T : new() 

// T는 MyBase의 파생클래스이어야 함
class MyClass<T> where T : MyBase

// T는 IComparable 인터페이스를 가져야 함
class MyClass<T> where T : IComparable

// 좀 더 복잡한 제약들
class EmployeeList<T> where T : Employee,
   IEmployee, IComparable<T>, new()
{
       ...
}

// 복수 타입 파라미터 제약
class MyClass<T, U> 
    where T : class 
    where U : struct
{
        ...
}
```

