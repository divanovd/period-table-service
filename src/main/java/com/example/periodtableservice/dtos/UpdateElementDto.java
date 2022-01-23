package com.example.periodtableservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateElementDto {
    private String allotropes;
    private String alternative_name;
    private String alternative_names;
    private String appearance;
    @JsonProperty("at_t_(k)")
    private String atTK;
    private String atomic_number;
    private String atomic_radius;
    private String atomic_weight;
    private String band_gap;
    private String boiling_point;
    private String brinell_hardness;
    private String bulk_modulus;
    private String cas_number;
    private String color;
    private String covalent_radius;
    private String critical_point;
    private String crystal_structure;
    private String curie_point;
    private String density_at_stp;
    private String density_near_rt;
    private String density_when_liquid_at_mp;
    private String discovery;
    private String discovery_and_first_isolation;
    private String electrical_resistivity;
    private String electron_configuration;
    private String electronegativity;
    private String element_category;
    private String first_isolation;
    private String group_block;
    private String heat_of_fusion;
    private String heat_of_vaporisation;
    private String heat_of_vaporization;
    private String ionisation_energies;
    private String ionization_energies;
    private String iso;
    private String magnetic_ordering;
    private String melting_point;
    private String mohs_hardness;
    private String molar_heat_capacity;
    private String molar_volume;
    private String name;
    private String name_symbol;
    private String named_by;
    private String naming;
    private String number;
    private String oxidation_states;
    @JsonProperty("p_(pa)")
    private String pPa;
    private String per_shell;
    private String period;
    private String phase;
    private String poisson_ratio;
    private String prediction;
    private String pronunciation;
    private String proposed_formal_name;
    private String recognised_as_an_element_by;
    private String recognized_as_a_distinct_element_by;
    private String recognized_as_a_unique_metal_by;
    private String recognized_as_an_element_by;
    private String shear_modulus;
    private String speed_of_sound;
    private String speed_of_sound_thin_rod;
    private String sublimation_point;
    private String symbol;
    private String tensile_strength;
    private String thermal_conductivity;
    private String thermal_diffusivity;
    private String thermal_expansion;
    private String triple_point;
    private String van_der_waals_radius;
    private String vickers_hardness;
    private String when_liquid_at_bp;
    private String when_liquid_at_mp;
    private String youngs_modulus;
}
