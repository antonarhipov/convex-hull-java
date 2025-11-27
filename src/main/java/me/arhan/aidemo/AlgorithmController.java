package me.arhan.aidemo;

import me.arhan.aidemo.custom.Algorithm;
import me.arhan.aidemo.custom.Coordinate;
import me.arhan.aidemo.custom.JarvisMarch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlgorithmController {

    @PostMapping("/convex")
    public ResponseEntity<List<Coordinate>> createDList(@RequestBody List<Coordinate> coordinates) {
        List<Coordinate> result = new JarvisMarch().run(coordinates);
        System.out.println("The convex contains " + result.size() + " points");
        return ResponseEntity.ok(result);
    }

}
