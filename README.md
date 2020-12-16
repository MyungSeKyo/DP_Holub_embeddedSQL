<Optional 확장>
1. SQL 문장에서 distinct 키워드 지원
1.1 추가된 코드
 
TableVisitor 인터페이스 추가하였다.
 
Table 인터페이스에 accept 메서드 추가하였다.
 
ConcreteTable 클래스에 accept 메서드를 추가하였고 Concrete한 visitor 클래스에서 사용하기 위해서  getRowSet 메서드를 추가하였다.
 
UnmodifiableTable 클래스에도 accept 메서드 추가하였다.
 
TableDistinctVisitor 클래스 추가하였다. 모든 엔트리를 순회하며 distinct 키워드가 지정된 모든 컬럼 값을 concatenation 한 것을 HashSet에 저장하고 중복을 검사하여 중복된 로우를 제거하였다.
 
Database 클래스에 DISTINCT 키워드 토큰을 어트리뷰트에 추가하였다.
 
Database 클래스의 statement 메서드의 SELECT 쿼리문을 파싱하는 부분에 DISTINC 포함 여부를 확인하는 로직을 추가하였다.
 
만약 DISTINCT 토큰이 포함되어 있으면 결과 테이블을 리턴하기 전에 TableSortingVisitor 오퍼레이션을 적용해준다.
1.2 실행 결과
 
위와 같이 잘 작동하는 것을 확인 할 수 있다.







1.3 테스트 코드
 
1.4 테스트 실행 결과
다음과 같이 예상되는 정확한 테이블의 결과를 스트링으로 저장하고 실제 쿼리를 실행한 결과 테이블과 비교하였다.





2. SQL 문장에서 order by 키워드 지원
2.1 추가된 코드
 
TableSortingVisitor를 추가하였다. 위 클래스는 생성자에서 정렬한 기준 컬럼을 키값으로 하고 ASC, DESC 여부를 1, -1로 밸류값으로 가지는  LinkedHashMap으로 받아 테이블의 로우를 정렬한다.
 
Database 클래스의 어트리뷰트에 order by 관련 토큰들 추가하였다.
 
Database의 statement 메서드의 select문 파싱 부분에 order by 파싱 로직 추가하였다. do-while문을 사용하여 복수개의 컬럼 값을 받을 수 있도록 하였다. LinkedHashMap을 사용하여 정렬 기준이 되는 컬럼을 키값으로 하고 ASC의 경우 1, DESC의 경우 -1을 저장하도록 하였다. 
ordering key가 있는 경우에 결과 테이블을 리턴하기 전에 TableSortingVisitor을 적용하고 리턴하게 된다.
2.2 실행 결과
 
위와 같이 잘 동작하는 것을 확인 할 수 있다.
2.3 테스트 코드
 
예상되는 정확한 테이블 결과값을 스트링으로 저장하고 실제 쿼리를 수행한 결과 테이블과 비교하였다.




3. Nested query 지원
3.1 추가된 코드
 
기존 Database 클래스의 statement 메서드의 select 파싱 부분을 따로 parseSelectStatement함수로 분리하였다.
 
FROM 토큰 뒤에 좌괄호 토큰이 있으면 분리한 parseSelectStatement 메서드를 재귀적으로 호출하여 nested query의 결과를 table로 받는다.
 
기존 Database 클래스의 doSelect 메서드가 테이블 이름 리스트를 받아서 처리하였었는데 위와 같이 테이블 이름이 아니라 테이블 객체 리스트를 받도록 수정하였다.
 
Select 파싱 부분을 분리한 재귀함수에서 nested query가 존재하면 nested query의 결과 테이블을 doSelect 함수로 넘겨주어 결과 테이블을 반환하고 nested query가 없으면 넘어온 테이블 이름들로 테이블 객체를 찾아 넘겨줘서 결과 테이블을 반환하도록 수정하였다.


3.2 실행 결과
 
위와 같이 잘 동작하는 것을 확인 할 수 있다.





3.3 테스트 코드
 
예상되는 정확한 테이블 값을 스트링으로 선언하여 실제 쿼리를 수행한 결과 테이블과 비교하였다.

4. 앞서 설명한 모든 Junit Test 결과
 
앞서 설명한 모드 Junit 테스트가 오류 없이 실행되는 것을 확인 할 수 있다.


















5. 수정 후 기존 기능 정상 동작 확인
 
기존 프로젝트의 ConcreteTable 클래스 내에는 위와 같이 Test 클래스 안에 test 메서드가 있다. insert, update, delete, select, store/load, join, undo에 대한 테스트가 실행 된다.

 
수정한 프로젝트와 원본 프로젝트에 대해서 기존의 동일한 테스트를 시행하였고 위와 같이 같은 테스트 결과를 반환하는 것을 확인함으로써 기존 기능들도 정상작동한다고 판단할 수 있다 .
