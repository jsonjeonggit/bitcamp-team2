/* ObjectInputStream와 ObjectOutputStream는 객체형태(ArrayList)를 입출력할 수 있다.
 * list에 있는 내용을 통째로 Input해서, Output으로 담은 것 전체를 출력해주는 것
 * list = (ArrayList<Contact>)in.readObject();
   => readObject가 Contact 형태의 ArrayList이다(타입캐스팅). 이걸 list에 담아라
 * out.writeObject(list); - 통째로 출력
 * */

package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactAddController implements Command {
  private ContactDao contactDao;

  public ContactAddController() {
    contactDao = ContactDao.getInstance();
  }
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (contactDao.existEmail(paramMap.get("email"))) {
      out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
      return;
    }
  
    Contact contact = new Contact();
    contact.setName(paramMap.get("name"));
    contact.setPosition(paramMap.get("position"));
    contact.setTel(paramMap.get("tel"));
    contact.setEmail(paramMap.get("email"));
  
    contactDao.insert(contact);
    out.println("등록하였습니다.");
      }
  }