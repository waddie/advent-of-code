(ns one.core-test
  (:require [clojure.test :refer :all]
            [one.core :refer :all]))

(deftest part-one-examples
  (testing "Examples for part one."
    (is (= 2 (one.core/all-modules [12])))
    (is (= 2 (one.core/all-modules [14])))
    (is (= 654 (one.core/all-modules [1969])))
    (is (= 33583 (one.core/all-modules [100756])))))

(deftest part-two-examples
  (testing "Examples for part two."
    (is (= 2 (one.core/all-modules-plus-extra-fuel [12])))
    (is (= 966 (one.core/all-modules-plus-extra-fuel [1969])))
    (is (= 50346 (one.core/all-modules-plus-extra-fuel [100756])))))
