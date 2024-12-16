import globals.Globals

DT = recipemap('distillation_tower')
SIEVE_DT = recipemap('sieve_distillation')
CRYSTALLIZER = recipemap('crystallizer')
EXTRACTOR = recipemap('extractor')
REFORMER = recipemap('catalytic_reformer_recipes')
FLUID_HEATER = recipemap('fluid_heater')

// Aromatics
    SIEVE_DT.recipeBuilder()
        .fluidInputs(fluid('reformate') * 1000)
        .fluidInputs(fluid('furfural') * 100)
        .fluidOutputs(fluid('btex_extract') * 800)
        .fluidOutputs(fluid('reformate_raffinate') * 300)
        .duration(25)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()
    
    SIEVE_DT.recipeBuilder()
        .fluidInputs(fluid('btex_extract') * 1000)
        .fluidInputs(fluid('dense_steam') * 100)
        .fluidOutputs(fluid('furfural_solution') * 200)
        .fluidOutputs(fluid('btex') * 1000)
        .duration(25)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

    DT.recipeBuilder()
        .fluidInputs(fluid('furfural_solution') * 200)
        .fluidOutputs(fluid('furfural') * 100)
        .fluidOutputs(fluid('water') * 100)
        .duration(25)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

    DT.recipeBuilder()
        .fluidInputs(fluid('btex') * 1000)
        .fluidOutputs(fluid('xylene') * 400)
        .fluidOutputs(fluid('ethylbenzene') * 50)
        .fluidOutputs(fluid('toluene') * 400)
        .fluidOutputs(fluid('benzene') * 150)
        .duration(100)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

    // Xylenes
        DT.recipeBuilder()
            .fluidInputs(fluid('xylene') * 1000)
            .fluidOutputs(fluid('ortho_xylene') * 200)
            .fluidOutputs(fluid('meta_para_xylene_mixture') * 800)
            .duration(100)
            .EUt(Globals.voltAmps[1] * 2)
            .buildAndRegister()

        CRYSTALLIZER.recipeBuilder()
            .fluidInputs(fluid('meta_para_xylene_mixture') * 4000)
            .outputs(metaitem('dustParaXylene'))
            .fluidOutputs(fluid('meta_xylene') * 3000)
            .duration(100)
            .EUt(Globals.voltAmps[1])
            .buildAndRegister()

        EXTRACTOR.recipeBuilder()
            .inputs(ore('dustParaXylene'))
            .fluidOutputs(fluid('para_xylene') * 1000)
            .duration(5)
            .EUt(30)
            .buildAndRegister()

        REFORMER.recipeBuilder()
            .notConsumable(metaitem('catalystBedZsmFive'))
            .fluidInputs(fluid('meta_xylene') * 1000)
            .fluidOutputs(fluid('para_xylene') * 1000)
            .duration(60)
            .EUt(Globals.voltAmps[1] * 2)
            .buildAndRegister()

    // Pyrolysis gasoline separation

        SIEVE_DT.recipeBuilder()
            .fluidInputs(fluid('pyrolysis_gasoline') * 1000)
            .fluidInputs(fluid('furfural') * 50)
            .fluidOutputs(fluid('btex_extract') * 400)
            .fluidOutputs(fluid('pyrolysis_raffinate') * 400)
            .fluidOutputs(fluid('c_five_fraction') * 200)
            .duration(60)
            .EUt(Globals.voltAmps[1])
            .buildAndRegister()

        // Dimerization of CPD

        FLUID_HEATER.recipeBuilder()
            .fluidInputs(fluid('c_five_fraction') * 1000)
            .fluidOutputs(fluid('dimerized_c_five_fraction') * 870)
            .duration(60)
            .EUt(Globals.voltAmps[1])
            .buildAndRegister()

        DT.recipeBuilder()
            .fluidInputs(fluid('dimerized_c_five_fraction') * 870)
            .fluidOutputs(fluid('dicyclopentadiene') * 130)
            .fluidOutputs(fluid('pentane') * 380)
            .fluidOutputs(fluid('isoprene') * 360)
            .duration(60)
            .EUt(Globals.voltAmps[1])
            .buildAndRegister()

        FLUID_HEATER.recipeBuilder()
            .fluidInputs(fluid('dicyclopentadiene') * 1000)
            .fluidOutputs(fluid('cyclopentadiene') * 2000)
            .duration(60)
            .EUt(Globals.voltAmps[1])
            .buildAndRegister()

// Butenes separation
DT.recipeBuilder()
        .fluidInputs(fluid('butene') * 1000)
        .fluidOutputs(fluid('isobutylene') * 500)
        .fluidOutputs(fluid('one_butene') * 300)
        .fluidOutputs(fluid('two_butene') * 200)
        .duration(300)
        .EUt(30)
        .buildAndRegister()