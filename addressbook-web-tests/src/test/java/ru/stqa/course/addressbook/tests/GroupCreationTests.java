package ru.stqa.course.addressbook.tests;



import org.testng.annotations.*;
import ru.stqa.course.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }


}
