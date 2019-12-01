(ns two.core-test
  (:require [clojure.test :refer :all]
            [two.core :refer :all]))

(deftest examples-part-one
  (testing "Example for part one."
    (is (= 12
           (two.core/checksum ["abcdef"
                               "bababc"
                               "abbcde"
                               "abcccd"
                               "aabcdd"
                               "abcdee"
                               "ababab"])))))
