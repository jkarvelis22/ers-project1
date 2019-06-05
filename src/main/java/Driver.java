import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Role;
import com.revature.models.User;

public class Driver {
 public static void main(String[] args) {
//	UserDAO ud = new UserDAO();
//	Role r = new Role(1, "manager");
//	User u = new User(14, "pvalenz", "mama", "PJ", "valenzuela", "puppydogssmiles@gmail.com", r);
//
//	System.out.println(ud.add(u));
//	 
	 ReimbursementDAO rd = new ReimbursementDAO();
	 ReimbursementStatus rs = new ReimbursementStatus(2, "pending");
	 ReimbursementType rt = new ReimbursementType(2, "pending");
	 Reimbursement re = new Reimbursement(11, 400, "yes", "submitted", "Went on a cruise", null, 2,1, rs, rt );
	 System.out.println(rd.add(re));
//	 Reimbursement rb = new Reimbursement(7, null, null, null, null, null, 2, 3, 2, 1);
	 
	System.out.println(rd.update(re));
	
	
}
}
