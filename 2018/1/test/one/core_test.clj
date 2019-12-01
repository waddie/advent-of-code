(ns one.core-test
  (:require [clojure.test :refer :all]
            [one.core :refer :all]))

(deftest part-one-examples
  (testing "Examples for part one."
    (is (= 3 (one.core/result-of-frequency-changes [1 1 1])))
    (is (= 0 (one.core/result-of-frequency-changes [1 1 -2])))
    (is (= -6 (one.core/result-of-frequency-changes [-1 -2 -3])))))

(deftest part-two-examples
  (testing "Examples for part two."
    (is (= 0 (one.core/first-duplicate-frequency-reached [1 -1])))
    (is (= 10 (one.core/first-duplicate-frequency-reached [3 3 4 -2 -4])))
    (is (= 5 (one.core/first-duplicate-frequency-reached [-6 3 8 5 -6])))
    (is (= 14 (one.core/first-duplicate-frequency-reached [7 7 -2 -7 -4])))))
