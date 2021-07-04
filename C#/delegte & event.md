# delegte & event

- delegate

```c#
namespace DelegateTest { 
    // 대리자를 만들어준다. 
    delegate void Del(); 
    // 매개변수가 없고 반환형이 void형인 메서드만 참조 시킬 수 있다.
    class Mng 
    { 
        public void Main() 
        {
        // myDel이라는 대리자 객체 생성 
        Del myDel; 
        // Print라는 메서드를 참조해준다. 
        myDel = Print; 
        // 대리자를 이용한 메서드 호출 myDel(); 
        // 메서드를 참조하는 형식은 여러가지를 지원합니다. 
        // myDel = new Del(Print); 
        // myDel = Print; 
        // myDel += Print; 
    	} 
        public void Print() 
        {
            Console.WriteLine("대리자를 통한 메서드 호출입니다."); 
        } 
    } 
}
```



```c#
    // myDel = new Del(Print); 
    // myDel = Print; 
    // myDel += Print; 
    //중요!!! 그래서 delegate 인스턴스를 인자로 사용할 때, new로 객체 생성을 안해줘도 된다.
```


- event

```c#
namespace Delegateex
{
    class Program
    {
        static void Main(string[] args)
        {
            Teacher a = new Teacher();
            
            a.Run();
        }
    }


    // 반환형이 없고 매개변수가 int형인 delegate를
    // namespace단에 선언합니다.
    public delegate void MyDel(int score); 

    class Teacher
    {
        public void Run()
        {

            // 학생 두명 생성
            Student1 stu1 = new Student1();
            Student2 stu2 = new Student2();

            // 각각의 학생의 Event(Delegate)에 메서드를 구독시킨다. 
            stu1.Stu1Event += GetScore;
            stu2.Stu2Event += GetScore;


            stu1.Run();
            stu2.Run();
        }

        private void GetScore(int score)
        {
            Console.WriteLine(string.Format("당신의 점수는 {0}점 입니다.",score));
        }
    }


    class Student1
    {
        // 네임스페이스 단에 선언된 Mydel을 이벤트형식으로 선언
        public event MyDel Stu1Event;

        public void Run()
        {
            // 콜백메서드 호출
            Stu1Event(80);
        }
    }


    class Student2
    {
        // 네임스페이스 단에 선언된 Mydel을 이벤트형식으로 선언
        public event MyDel Stu2Event;

        public void Run()
        {
            // 콜백메서드 호출
            Stu2Event(100);
        }
    }
}

```

