package wb3.core.config;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

public class SystemArchitecture {

    public static final String repoPointCut = "execution(* (@org.springframework.stereotype.Repository *).*(..))";
    public static final String svcPointCut = "execution(* (@org.springframework.stereotype.Service *).*(..))";
    public static final String ctrlPointCut = "execution(* (@org.springframework.web.bind.annotation.RestController *).*(..))";

    @Pointcut(repoPointCut)
    public void Repository() {}

    @Pointcut(svcPointCut)
    public void Service() {}

    @Pointcut(ctrlPointCut)
    public void Controller() {}

}
