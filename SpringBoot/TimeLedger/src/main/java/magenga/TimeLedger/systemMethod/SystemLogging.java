package magenga.TimeLedger.systemMethod;

import magenga.TimeLedger.common.UserQueryService;
import magenga.TimeLedger.common.dao.UserDao;
import magenga.TimeLedger.common.entity.User;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class SystemLogging {

    private UserQueryService userQueryService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(SystemLogging.class);

    public void userLogIn(User user) {

        logger.info("userSeq :" + user.getUserSeq() + " logged in.");
    }

    public void userLogInFailed(User user) {
        if(userQueryService.findSeqByAccount(user.getAccount()) != null) {
            logger.info("userSeq :" + user.getUserSeq() + " logged in failed by wrong password.");
        }else{
            logger.info("someone typed wrong account.");
        }
    }

    public void userSignUp(User user) {

        logger.info("userSeq :" + user.getUserSeq() + " had sign up.");
    }

    public void startedTimes () {
        try {
            //讀取檔案
            Path filePath = Paths.get("src/main/localData/startUpCounter.txt");
            int count = Integer.parseInt(Files.readString(filePath));
            count++;
            Files.writeString(filePath, Integer.toString(count));
            logger.info("System have started " + count + " times");
        } catch (IOException e) {
            // 處理異常，比如打印錯誤、記錄或者將錯誤傳遞給調用者
            logger.info("Counter file not Found.\nThis operation would not record");


        }
    }
}
