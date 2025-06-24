package jpabook.jpashop.jpql;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect extends H2Dialect { // 커스텀 방식 사용 시 환경설정의 dialect 부분도 이 클래스로 변경해줘야 함

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions
                .getFunctionRegistry()
                .register("group_concat",
                        new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }

}
