package javaschool.com.registrationSystem.controller;

import javaschool.com.registrationSystem.model.Customer;
import javaschool.com.registrationSystem.model.ResultType;
import javaschool.com.registrationSystem.model.UpdateResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    public static final String DELETE_RESPONSE = " zostal poprawnie usuniety";

    @GetMapping("/customer")
    @ResponseBody
    public Customer getCustomer() {

        Customer customer = new Customer();
        customer.setName("Kamil");
        customer.setSurname("Michalski");

        return customer;
    }

    @PostMapping("/customer")
    @ResponseBody
    public ResultType CreateCustomer(@RequestBody Customer customer) {

        ResultType result = new ResultType();
        if (customer.getName().length() > 0 && customer.getSurname().length() > 0) {
            System.out.println(customer.toString());
            //Zapisz do bazy danych
            result.setErrorCode("0");
            result.setErrorDesc("0");
        } else if (customer.getName().length() > 0 && customer.getSurname().length() == 0) {
            result.setErrorCode("-1");
            result.setErrorDesc("Uzupełnij nazwisko.");
        } else if (customer.getName().length() == 0 && customer.getSurname().length() > 0) {
            result.setErrorCode("-1");
            result.setErrorDesc("Uzupełnij imie.");
        } else {
            result.setErrorCode("-1");
            result.setErrorDesc("Main error");
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/customer")
    @ResponseBody
    public ResultType deleteCustomer(@RequestBody Customer customer) {
        ResultType result = new ResultType();
        if (customer.getId() != null) {
            //usun usera
            result.setErrorCode("0");
            result.setErrorDesc(customer.getName() + customer.getSurname() + DELETE_RESPONSE);
        } else {
            result.setErrorCode("0");
            result.setErrorDesc("Popraw dane wejsciowe");
        }

        return result;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/customer")
    public UpdateResult<Customer, ResultType> updateCustomer(@RequestBody Customer customer) {
        ResultType result = new ResultType();
        if (customer.getId() != null && customer.getName().length() > 0
        && customer.getSurname().length() > 0){
            result.setErrorCode("0");
            result.setErrorDesc("Zmodyfikowano uzytkownika" + customer.getName());
        }else {
            result.setErrorCode("-1");
            result.setErrorDesc("Popraw dane wejsciowe.");
        }


        UpdateResult<Customer, ResultType> updateResult = new UpdateResult<>();
        updateResult.setObject(customer);
        updateResult.setResultObject(result);

        return updateResult;
    }
}
