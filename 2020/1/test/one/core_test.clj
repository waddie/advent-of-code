(ns one.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [one.core :refer :all]))

(def example-array [1721 979 366 299 675 1456])

(deftest example-for-part-one
  (testing "Example for part one."
    (is (= 514579
           (reduce * (one.core/find-pair-equal-to-sum example-array
                                                      one.core/target-number))))))

(deftest example-for-part-two
  (testing "Example for part two."
    (is (= 241861950
           (reduce * (one.core/find-triplet-equal-to-sum example-array
                                                         one.core/target-number))))))
