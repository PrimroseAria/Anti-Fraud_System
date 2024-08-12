package antifraud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AntiFraudController {


    @PostMapping("/api/antifraud/transaction")
    public ResponseEntity<TransactionResponse> postAmount(@RequestBody AmountRequest input) {

        long amount = input.getAmount();
        if (amount <= 200 && amount > 0) {
            return new ResponseEntity<>(new TransactionResponse("ALLOWED"), HttpStatus.OK);

        } else if (amount <= 1500 && amount > 200) {
            return new ResponseEntity<>(new TransactionResponse("MANUAL_PROCESSING"), HttpStatus.OK);

        } else if (amount > 1500){
            return new ResponseEntity<>(new TransactionResponse("PROHIBITED"), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
