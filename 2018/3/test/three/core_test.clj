(ns three.core-test
  (:require [clojure.test :refer :all]
            [three.core :refer :all]))

(deftest example-for-part-one
  (testing "Example for part one."
    (is (= 4 (three.core/multiple-patched-inches ["#1 @ 1,3: 4x4"
                                                  "#2 @ 3,1: 4x4"
                                                  "#3 @ 5,5: 2x2"])))))

(deftest example-for-part-two
  (testing "Example for part two."
    (is (= 3 (three.core/unique-claim ["#1 @ 1,3: 4x4"
                                       "#2 @ 3,1: 4x4"
                                       "#3 @ 5,5: 2x2"])))))
