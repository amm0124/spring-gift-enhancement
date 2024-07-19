package gift.controller;


import gift.dto.OptionRequestDTO;
import gift.dto.OptionResponseDTO;
import gift.entity.Option;
import gift.repository.OptionRepository;
import gift.service.OptionService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("options")
    public ResponseEntity<List<Option>> getOptions() {
        List<Option> options = optionService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(options);
    }

    @GetMapping("options/{productId}")
    public ResponseEntity<List<OptionResponseDTO>> getOptionsByProductId(@PathVariable Long productId) {
        List<OptionResponseDTO> options = optionService.findByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(options);
    }

    @PostMapping("options/{productId}")
    public ResponseEntity<String> createOption(@PathVariable Long productId, @Valid @RequestBody OptionRequestDTO optionRequestDTO) {
        optionService.save(productId, optionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("상품 option 등록 완료");
    }


}
