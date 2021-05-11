package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/5 10:41
 */
public class ResultJSONUtils {
    //给前端返回json数据
    public static void write(HttpServletResponse response, HashMap<String,Object> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer=response.getWriter();
        ObjectMapper mapper=new ObjectMapper();
        writer.println(mapper.writeValueAsString(map));
    }
}
