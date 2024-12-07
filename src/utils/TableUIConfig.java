package utils;

import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.UserRepositoryImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TableUIConfig {
    public static Table getTable(List<User> users){
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        String [] columnNames = {"ID","User Name","Email","Password","Is Deleted"};
        // Column added
        for(int i=0;i<5;i++){
            table.addCell(columnNames[i], new CellStyle(CellStyle.HorizontalAlign.center));
            table.setColumnWidth(i,10,30);
        }
//        reverse to allow new user to be on the top
        Collections.reverse(users);
        for (User user: users){
            table.addCell(user.getId().toString(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(user.getUserName(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(user.getEmail(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(user.getPassword(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(user.getDeleted().toString(), new CellStyle(CellStyle.HorizontalAlign.center));
        }
        return table;
    }
}
