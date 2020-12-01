(ns one.core-test
  (:require [clojure.test :refer :all]
            [one.core :refer :all]))

(def example-array [1721 979 366 299 675 1456])
(def example-one-answer 514579)
(def example-two-answer 241861950)

(deftest example-for-part-one
  (testing "Example for part one."
    (is (= example-one-answer
           (reduce * (one.core/find-pair-equal-to-sum example-array
                                                      one.core/target-number))))))

(deftest example-for-part-two
  (testing "Example for part two."
    (is (= example-two-answer
           (reduce * (one.core/find-triplet-equal-to-sum example-array
                                                         one.core/target-number))))))
