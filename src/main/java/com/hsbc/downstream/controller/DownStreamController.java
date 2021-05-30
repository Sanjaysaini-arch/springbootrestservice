package com.hsbc.downstream.controller;




import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.downstream.dto.DownStreamdto;
import com.hsbc.downstream.exceptionHandlers.DownStreamDtoException;
import com.hsbc.downstream.repo.DownStreamTransactionRecordRepository;

@RestController
class DownStreamController {
private static final Logger logger = LoggerFactory.getLogger("DownStreamController");
	   
		
  private final DownStreamTransactionRecordRepository repository;

  DownStreamController(DownStreamTransactionRecordRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/getDownStreamDto")
  List<DownStreamdto> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/postDownStreamDto")
  DownStreamdto newEmployee(@RequestBody DownStreamdto downStreamdto) {
	  logger.info("request recieved"+downStreamdto);
    return repository.save(downStreamdto);
  }

  // Single item
  
  @GetMapping("/getDownStreamDto/{id}")
  DownStreamdto one(@PathVariable int id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new DownStreamDtoException(id));
  }

  @PutMapping("/createDownStreamDto/{id}")
  DownStreamdto replaceEmployee(@RequestBody DownStreamdto newdownStreamdto, @PathVariable int id) {
    
    return repository.findById(id)
      .map(downStreamdto -> {
    	  downStreamdto.setFirstName(newdownStreamdto.getFirstName());
    	  downStreamdto.setLastName(newdownStreamdto.getLastName());
        return repository.save(downStreamdto);
      })
      .orElseGet(() -> {
    	  newdownStreamdto.setId(id);
        return repository.save(newdownStreamdto);
      });
  }

  @DeleteMapping("/deleteDownStreamDto/{id}")
  void deleteEmployee(@PathVariable int id) {
    repository.deleteById(id);
  }
}
