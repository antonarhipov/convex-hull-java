package me.arhan.aidemo;

import me.arhan.aidemo.math.Coordinate;
import me.arhan.aidemo.math.TheAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlgorithmController {

    @PostMapping("/convex")
    public ResponseEntity<List<Coordinate>> createDList(@RequestBody List<Coordinate> coordinates) {
        Coordinate[] input = coordinates.toArray(new Coordinate[0]);
        List<Coordinate> result = TheAlgorithm.run(input);
        System.out.println("The convex contains " + result.size() + " points");
        return ResponseEntity.ok(result);
    }

}
