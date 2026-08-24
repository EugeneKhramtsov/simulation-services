package uu.app.repositoryservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uu.app.repositoryservice.dto.DataDto;
import uu.app.repositoryservice.service.DataService;

@Slf4j
@RestController
@RequestMapping("/simulation/data")
@AllArgsConstructor
public class DataController {

    private final DataService service;

    @GetMapping
    public DataDto getData(@RequestParam Long id) {
        return service.getData(id)
                .orElse(null);
    }

    @PostMapping
    public DataDto saveData(@RequestBody DataDto dto) {
        return service.saveData(dto);
    }

    @PutMapping
    public DataDto updateData(@RequestParam Long id, @RequestBody DataDto dto) {
        return service.updateData(id, dto);
    }

    @DeleteMapping
    public void deleteData(@RequestParam Long id) {
        service.deleteData(id);
    }
}
