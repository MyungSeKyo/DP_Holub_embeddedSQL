<Must 확장 기능>
[HTML Exporter 개발]
코드: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/src/com/holub/database/HTMLExporter.java
테스트: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/tests/com/holub/database/ConcreteTableTest.java#L29-L62

[XML Exporter 개발]
코드: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/src/com/holub/database/XMLExporter.java
테스트: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/tests/com/holub/database/ConcreteTableTest.java#L63-L78

[XML Importer 개발]
코드: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/src/com/holub/database/XMLImporter.java
테스트: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/tests/com/holub/database/ConcreteTableTest.java#L79-L87

[Join 오류 수정]
코드: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/src/com/holub/database/ConcreteTable.java#L469-L477
테스트: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/tests/com/holub/database/ConcreteTableTest.java#L88-L102


<Optional 확장>
[SQL 문에서 DISTINCT 지원 & Visitor Patter]
코드: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/src/com/holub/database/TableDistinctVisitor.java
테스트: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/tests/com/holub/database/ConcreteTableTest.java#L88-L102

[SQL 문에서 ORDER BY 지원 & Visitor Patter]
코드: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/src/com/holub/database/TableSortingVisitor.java
테스트: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/tests/com/holub/database/ConcreteTableTest.java#L115-L128

[Nested Query 지원]
코드: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/src/com/holub/database/Database.java#L895-L944
테스트: https://github.com/MyungSeKyo/DP_Holub_embeddedSQL/blob/main/tests/com/holub/database/ConcreteTableTest.java#L129-L140


